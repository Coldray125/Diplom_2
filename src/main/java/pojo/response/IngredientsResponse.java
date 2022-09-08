package pojo.response;

import java.util.List;

public class IngredientsResponse {
    private String success;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    List <Data> data;

    public IngredientsResponse(String success, List<Data> data) {
        this.success = success;
        this.data = data;
    }

    public IngredientsResponse() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public class Data {

        private String _id;
        private String name;
        private String type;
        private Integer proteins;
        private Integer fat;
        private Integer carbohydrates;
        private Integer calories;
        private Integer price;
        private String image;
        private String imageMobile;
        private String imageLarge;
        private Integer __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getProteins() {
            return proteins;
        }

        public void setProteins(Integer proteins) {
            this.proteins = proteins;
        }

        public Integer getFat() {
            return fat;
        }

        public void setFat(Integer fat) {
            this.fat = fat;
        }

        public Integer getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(Integer carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

        public Integer getCalories() {
            return calories;
        }

        public void setCalories(Integer calories) {
            this.calories = calories;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImageMobile() {
            return imageMobile;
        }

        public void setImageMobile(String imageMobile) {
            this.imageMobile = imageMobile;
        }

        public String getImageLarge() {
            return imageLarge;
        }

        public void setImageLarge(String imageLarge) {
            this.imageLarge = imageLarge;
        }

        public Integer get__v() {
            return __v;
        }

        public void set__v(Integer __v) {
            this.__v = __v;
        }

        public Data(String _id, String name, String type, Integer proteins, Integer fat, Integer carbohydrates, Integer calories, Integer price, String image, String imageMobile, String imageLarge, Integer __v) {
            this._id = _id;
            this.name = name;
            this.type = type;
            this.proteins = proteins;
            this.fat = fat;
            this.carbohydrates = carbohydrates;
            this.calories = calories;
            this.price = price;
            this.image = image;
            this.imageMobile = imageMobile;
            this.imageLarge = imageLarge;
            this.__v = __v;
        }

        public Data() {
        }
    }
}
