package fun.ticsmyc.pojo;


import java.util.List;



public class AreaStat {
    @Override
    public String toString() {
        return "AreaStat{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceShortName='" + provinceShortName + '\'' +
                ", confirmedCount=" + confirmedCount +
                ", suspectedCount=" + suspectedCount +
                ", curedCount=" + curedCount +
                ", deadCount=" + deadCount +
                ", comment='" + comment + '\'' +
                ", cities=" + cities +
                '}';
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceShortName() {
        return provinceShortName;
    }

    public void setProvinceShortName(String provinceShortName) {
        this.provinceShortName = provinceShortName;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public int getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(int suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public int getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(int curedCount) {
        this.curedCount = curedCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(List<CitiesBean> cities) {
        this.cities = cities;
    }

    /**
     * provinceName : 湖北省
     * provinceShortName : 湖北
     * confirmedCount : 549
     * suspectedCount : 0
     * curedCount : 31
     * deadCount : 24
     * comment :
     * cities : [{"cityName":"武汉","confirmedCount":495,"suspectedCount":0,"curedCount":31,"deadCount":23},{"cityName":"孝感","confirmedCount":22,"suspectedCount":0,"curedCount":0,"deadCount":0},{"cityName":"黄冈","confirmedCount":12,"suspectedCount":0,"curedCount":0,"deadCount":0},{"cityName":"荆州","confirmedCount":8,"suspectedCount":0,"curedCount":0,"deadCount":0},{"cityName":"荆门","confirmedCount":8,"suspectedCount":0,"curedCount":0,"deadCount":0},{"cityName":"仙桃","confirmedCount":2,"suspectedCount":0,"curedCount":0,"deadCount":0},{"cityName":"宜昌","confirmedCount":1,"suspectedCount":0,"curedCount":0,"deadCount":1},{"cityName":"十堰","confirmedCount":1,"suspectedCount":0,"curedCount":0,"deadCount":0}]
     */

    private String provinceName;
    private String provinceShortName;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private String comment;
    private List<CitiesBean> cities;


    public static class CitiesBean {
        /**
         * cityName : 武汉
         * confirmedCount : 495
         * suspectedCount : 0
         * curedCount : 31
         * deadCount : 23
         */

        private String cityName;
        private int confirmedCount;
        private int suspectedCount;
        private int curedCount;
        private int deadCount;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getConfirmedCount() {
            return confirmedCount;
        }

        public void setConfirmedCount(int confirmedCount) {
            this.confirmedCount = confirmedCount;
        }

        public int getSuspectedCount() {
            return suspectedCount;
        }

        public void setSuspectedCount(int suspectedCount) {
            this.suspectedCount = suspectedCount;
        }

        public int getCuredCount() {
            return curedCount;
        }

        public void setCuredCount(int curedCount) {
            this.curedCount = curedCount;
        }

        public int getDeadCount() {
            return deadCount;
        }

        public void setDeadCount(int deadCount) {
            this.deadCount = deadCount;
        }

        @Override
        public String toString() {
            return "CitiesBean{" +
                    "cityName='" + cityName + '\'' +
                    ", confirmedCount=" + confirmedCount +
                    ", suspectedCount=" + suspectedCount +
                    ", curedCount=" + curedCount +
                    ", deadCount=" + deadCount +
                    '}';
        }
    }
}
