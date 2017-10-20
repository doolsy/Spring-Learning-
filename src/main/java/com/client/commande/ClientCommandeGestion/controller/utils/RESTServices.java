package com.client.commande.ClientCommandeGestion.controller.utils;

/**
 * Created by asy on 19/10/2017.
 */
public class RESTServices {
    public RESTServices() {
    }

    public static final class ROOT {
        public static final String PATH_ROOT = "/commandeGestion";
        public static final String AUTHENTICATE = "/authenticate";
        public static final String SOCIAL = "/social";

        public ROOT() {
        }

        public static final class USERS {
            public static final String REGISTER = "/users/register";
            public static final String GET_CONNECTED = "/users/getConnected";
            public static final String FIND_BY_ID = "/illicar/users/findByID";
            public static final String REGISTER_TOKEN = "/commandeGestion/users/registertoken";

            public USERS() {
            }
        }
    }
}

