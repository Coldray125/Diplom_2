package pojo.response;

import java.util.List;

public class UserOrdersResponse {
    private String success;
    private List<Order> orders;
    private Integer total;
    private Integer totalToday;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(Integer totalToday) {
        this.totalToday = totalToday;
    }

    public UserOrdersResponse(String success, List<Order> orders, Integer total, Integer totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public UserOrdersResponse() {
    }

    public class Order {

        private String id;
        private List<String> ingredients = null;
        private String status;
        private String name;
        private String createdAt;
        private String updatedAt;
        private Integer number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public Order(String id, List<String> ingredients, String status, String name, String createdAt, String updatedAt, Integer number) {
            this.id = id;
            this.ingredients = ingredients;
            this.status = status;
            this.name = name;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.number = number;
        }

        public Order() {
        }
    }
}


