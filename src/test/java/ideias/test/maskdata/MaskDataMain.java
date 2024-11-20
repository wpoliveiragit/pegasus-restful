package ideias.test.maskdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MaskDataMain {

	public static void main(String[] args) {
		final Gson gson = new GsonBuilder().registerTypeAdapter(MyUser.class, new SensitiveFieldsAdapter<>()).create();

		MyUser user = new MyUser("userLogin", "userPass", 20, new HashMap<>(10, 1), new ArrayList<>(),
				new MyUser("userBLogin", "userBPass", 100, null, null, null));

		Map<String, Object> map = user.getMap2();
		map.put("Map-Integer", 2);
		map.put("Map-String", "string");
		map.put("Map-Double", 2.22);
		map.put("Map-Boolean", false);
		map.put("Map-Character", 'M');

		map.put("Map-Map", Map.of("Map-Map-bla", "bla"));
		map.put("Map-List", Arrays.asList("Map-List-bla", "Map-List-ble", "Map-List-bli"));
		map.put("Map-User", new MyUser("Map-User-Login", "Map-User-Pass", 30, null, null, null));

		List<Object> list = user.getList2();
		list.add(1);
		list.add("string");
		list.add(1.11);
		list.add(true);
		list.add('L');

		list.add(new HashMap<>(Map.of("List-Map-bla", "bla")));
		list.add(Arrays.asList("List-List-bla", "List-List-ble", "List-List-bli"));
		list.add(new MyUser("List-User-Login", "List-User-Pass", 40, null, null, null));

		System.out.println(gson.toJson(user));
	}

}
