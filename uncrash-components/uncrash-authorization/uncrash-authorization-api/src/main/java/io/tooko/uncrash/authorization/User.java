package io.tooko.uncrash.authorization;

import java.io.Serializable;

public interface User extends Serializable {

    String getId();

    String getUsername();

    String getName();

    String getType();

    String getRole();

}
