package common;

import common.customer.model.CustomerModel;
import common.user.model.UserModel;
import play.libs.typedmap.TypedKey;

public class Attrs {

    public static final String AUTH_HEADER = "X-AUTH";
    public static final TypedKey<UserModel> USER = TypedKey.create("user");
    public static final TypedKey<CustomerModel> CUSTOMER = TypedKey.create("customer");


}
