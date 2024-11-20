package ideias.test.maskdata;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SensitiveFieldsAdapter<T> implements JsonSerializer<T> {

	@Override
	public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObj = new JsonObject();
		try {
			serialize(jsonObj, src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}

	private void serialize(JsonObject jsonObjParan, Object objParan) throws Exception {
		for (Field field : objParan.getClass().getDeclaredFields()) {

			field.setAccessible(true); // Torna o campo private acessível
			Object value = field.get(objParan);
			String key = field.getName();

			if (value == null) {
				continue;
			}

			if (checkMaskData(jsonObjParan, field)) {
				continue;
			}

			if (checkPrimitive(jsonObjParan, key, value)) {
				continue;
			}

			JsonObject jsonObjCheckMap = checkMap(value);
			if (jsonObjCheckMap != null) {
				jsonObjParan.add(key, jsonObjCheckMap);
				continue;
			}

			JsonArray jsonArrayCheckList = checkList(value);
			if (jsonArrayCheckList != null) {
				jsonObjParan.add(key, jsonArrayCheckList);
				continue;
			}

			JsonObject jsonObj = new JsonObject();
			serialize(jsonObj, value);
			if (!jsonObj.isEmpty()) {
				jsonObjParan.add(key, jsonObj);
			}
		}
	}

	private boolean checkMaskData(JsonObject jsonObj, Field field) {
		if (field.isAnnotationPresent(MaskData.class)) {
			jsonObj.addProperty(field.getName(), field.getAnnotation(MaskData.class).maskText());
			return true;
		}
		return false;
	}

	private boolean checkPrimitive(JsonObject jsonObj, String key, Object value) {
		if (value instanceof String) {// STRING
			jsonObj.addProperty(key, (String) value);
			return true;
		}
		if (value instanceof Number) {// NUMBER
			jsonObj.addProperty(key, (Number) value);
			return true;
		}
		if (value instanceof Character) {// CHARACTER
			jsonObj.addProperty(key, (Character) value);
			return true;
		}
		if (value instanceof Boolean) {// BOOLEAN
			jsonObj.addProperty(key, (Boolean) value);
			return true;
		}
		return false;
	}

	private boolean checkPrimitiveList(JsonArray jsonArray, Object value) {
		if (value instanceof String) {// STRING
			jsonArray.add((String) value);
			return true;
		}
		if (value instanceof Number) {// NUMBER
			jsonArray.add((Number) value);
			return true;
		}
		if (value instanceof Character) {// CHARACTER
			jsonArray.add((Character) value);
			return true;
		}
		if (value instanceof Boolean) {// BOOLEAN
			jsonArray.add((Boolean) value);
			return true;
		}
		return false;
	}

	private JsonArray checkList(Object value) throws Exception {
		if (value instanceof List) {
			JsonArray jsonArray = new JsonArray();
			for (Object valueList : ((List<?>) value)) {
				if (checkPrimitiveList(jsonArray, valueList)) {
					continue;
				}

				final JsonObject jsonObjMap = checkMap(valueList);
				if (jsonObjMap != null) {
					jsonArray.add(jsonObjMap);
					continue;
				}

				final JsonArray jsonArrayList = checkList(valueList);
				if (jsonArrayList != null) {
					jsonArray.add(jsonArrayList);
					continue;
				}

				final JsonObject jsonObj = new JsonObject();
				serialize(jsonObj, valueList);
				if (!jsonObj.isEmpty()) {
					jsonArray.add(jsonObj);
					continue;
				}
			}
			return jsonArray.isEmpty() ? null : jsonArray;
		}
		return null;
	}

	private JsonObject checkMap(Object value) throws Exception {

		if (value instanceof Map) {
			JsonObject jsonObjMap = new JsonObject();

			for (Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
				String keyMap = entry.getKey().toString();
				Object valueMap = entry.getValue();

				if (checkPrimitive(jsonObjMap, keyMap, valueMap)) {// primitive
					continue;
				}

				JsonObject jsonObjMapSub = checkMap(valueMap);
				if (jsonObjMapSub != null) {// map
					jsonObjMap.add(keyMap, jsonObjMapSub);
					continue;
				}
				JsonArray jsonArraySub = checkList(valueMap);
				if (jsonArraySub != null) {// list
					jsonObjMap.add(keyMap, jsonArraySub);
					continue;
				}

				JsonObject jsonObj = new JsonObject();
				serialize(jsonObj, valueMap);
				jsonObjMap.add(keyMap, jsonObj);
//				jsonObjMap.addProperty(keyMap, "#ERR-MAP");
			}
			return jsonObjMap.isEmpty() ? null : jsonObjMap;
		}
		return null;
	}

}