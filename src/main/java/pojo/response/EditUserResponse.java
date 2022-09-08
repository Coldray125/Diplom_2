package pojo.response;

public class EditUserResponse {

    private String success;
    User user;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EditUserResponse(String success, User user) {
        this.success = success;
        this.user = user;
    }

    public EditUserResponse() {
    }

    public class User {
        private String email;
        private String name;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(String email, String name) {
            this.email = email;
            this.name = name;
        }
    }
}