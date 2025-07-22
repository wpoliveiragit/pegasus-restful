package br.com.pegasus.api.restful.util;

public final class AppConsts {
    public static final String MSG_TOOLS_TO_DAMAGE_TYPE;
    public static final String MSG_TOOLS_TO_RESISTANCE_TYPE;
    public static final String MSG_TOOLS_TO_CHARACTER_TYPE;
    public static final String MSG_TOOLS_TO_ATTRIBUTES_TYPE;
    public static final String MSG_TOOLS_TO_PAGINATION_TYPE;
    public static final String MSG_TOOLS_TO_CHARACTER_MODEL;
    public static final String MSG_CHAR_SERVICE_FIND_BY_ID;

    static {
        String modelType = " :: (model → type)";
        String typeModel = " :: (type → model)";
        String PREFIX = "Problemas na conversão :: ";

        MSG_TOOLS_TO_CHARACTER_TYPE = PREFIX + "PERSONAGEM" + modelType;
        MSG_TOOLS_TO_ATTRIBUTES_TYPE = PREFIX + "ATRIBUTO" + modelType;
        MSG_TOOLS_TO_DAMAGE_TYPE = PREFIX + "DANO" + modelType;
        MSG_TOOLS_TO_RESISTANCE_TYPE = PREFIX + "RESISTÊNCIA" + modelType;
        MSG_TOOLS_TO_PAGINATION_TYPE = PREFIX + "PAGINAÇÃO" + modelType;

        MSG_TOOLS_TO_CHARACTER_MODEL = PREFIX + "PERSONAGEM" + typeModel;
        MSG_CHAR_SERVICE_FIND_BY_ID = "Persoagem não encontrado!";
    }
}
