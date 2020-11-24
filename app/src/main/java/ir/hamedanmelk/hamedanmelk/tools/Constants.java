package ir.hamedanmelk.hamedanmelk.tools;

public class Constants {

    //HttpRequestHandler
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";

    //JSON Response Fields
    public static final String JSON_RESPONSE_DATA = "Data";
    public static final String JSON_RESPONSE_STATE = "State";
    //User login
    public static final String USER_MODEL_ID = "id";
    public static final String USER_MODEL_EMAIL= "email";
    public static final String USER_MODEL_FIRST_NAME= "first_name";
    public static final String USER_MODEL_LAST_NAME= "last_name";
    public static final String USER_MODEL_IMAGE= "Image";
    public static final String[] USER_MODEL_FIELDS={"id","email","first_name","last_name","Image"};

    //RegisterPublicUser
    public static final String REGISTER_PUBLIC_USER_FIRST_NAME="FirstName";
    public static final String REGISTER_PUBLIC_USER_LAST_NAME="LastName";
    public static final String REGISTER_PUBLIC_USER_GENDER_TYPE="GenderType";
    public static final String REGISTER_PUBLIC_USER_MOBILE="Mobile";
    public static final String[] REGISTER_PUBLIC_USER_MODEL_FIELDS={"FirstName","LastName","GenderType","Mobile"};

    //Rent model
    public static final String RENT_MODEL_ID="id";
    public static final String RENT_MODEL_TITLE="Title";
    public static final String RENT_MODEL_LAND_STATE_ID="land_state_id";
    public static final String RENT_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String RENT_MODEL_VIEW="View";
    public static final String RENT_MODEL_LAND_STATE_TITLE="LandStateTitle";
    public static final String RENT_MODEL_LAND_SITUATION_TITLE="LandSituationTitle";
    public static final String RENT_MODEL_LAND_SITUATION_COLOR="LandSituationColor";
    public static final String RENT_MODEL_FIRST_NAME="first_name";
    public static final String RENT_MODEL_LAST_NAME="last_name";
    public static final String[] RENT_MODEL_FIELDS={"id","Title","land_state_id","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};
    //Lands model
    public static final String LAND_MODEL_ID="id";
    public static final String LAND_MODEL_TITLE="Title";
    public static final String LAND_MODEL_LAND_STATE_ID="land_state_id";
    public static final String LAND_MODEL_CREATED_AT="created_at";
    public static final String LAND_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String LAND_MODEL_VIEW="View";
    public static final String LAND_MODEL_LANDSTATETITLE="LandStateTitle";
    public static final String LAND_MODEL_LANDSITUATIONTITLE="LandSituationTitle";
    public static final String LAND_MODEL_LANDSITUATIONCOLOR="LandSituationColor";
    public static final String LAND_MODEL_FIRST_NAME="first_name";
    public static final String LAND_MODEL_LAST_NAME="last_name";
    public static final String[] LAND_MODEL_FIELDS={"ID","Title","land_state_id","created_at","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};
}
