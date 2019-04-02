package net.uncrash.authorization;

import java.io.Serializable;

public interface Role extends Serializable {

    String getId();

    String getRoleName();

}
