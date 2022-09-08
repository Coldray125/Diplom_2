package pojo.response;

public class AuthUserResponse {

    private String refreshToken;
    private String accessToken;
    private String success;
    User user;

    public AuthUserResponse(String refreshToken, String accessToken, String success) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.success = success;
    }

    public AuthUserResponse() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public User getUser() {
        return user;
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

        public User() {
        }
    }
}