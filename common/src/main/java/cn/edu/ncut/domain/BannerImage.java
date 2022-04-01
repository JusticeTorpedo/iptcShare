package cn.edu.ncut.domain;

public class BannerImage {
    private Integer imageId;
    private String imageName;
    private String imagePath;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "BannerImage{" +
                "imageId=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
