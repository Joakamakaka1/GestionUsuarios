package Act01.repository;

import Act01.model.User;

public interface UserRepositoryAPI {

    User insertUser(String gmail, String password, boolean isAdmin);

    User getUser(String gmail);

    User updateUser(User u);

    boolean deleteUser(String gmail);
}
