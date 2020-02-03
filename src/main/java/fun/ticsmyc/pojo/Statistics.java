package fun.ticsmyc.pojo;


import java.util.Objects;


public class Statistics {

    /**
     * id : 1
     * createTime : 1579537899000
     * modifyTime : 1579860497000
     * infectSource : 野生动物，可能中华菊头蝠
     * passWay : 未完全掌握，存在人传人、医务人员感染、一定范围社区传播
     * imgUrl : https://img1.dxycdn.com/2020/0123/733/3392575782185696736-73.jpg
     * dailyPic : https://img1.dxycdn.com/2020/0124/981/3392719124572016783-73.jpg
     * summary :
     * deleted : false
     * countRemark : 全国 确诊 887 例 疑似 1076 例 治愈 35 例 死亡 26 例
     * virus : 新型冠状病毒 2019-nCoV
     * remark1 : 病毒是否变异：存在可能
     * remark2 : 疫情是否扩散：是
     * remark3 :
     * remark4 :
     * remark5 :
     */

    private int id;
    private long createTime;
    private long modifyTime;
    private String infectSource;
    private String passWay;
    private String imgUrl;
    private String dailyPic;
    private String summary;
    private boolean deleted;
    private String countRemark;
    private String virus;
    private String remark1;
    private String remark2;

    private String remark3;

    private String remark4;
    private String remark5;
    private String generalRemark;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private int seriousCount;

    private int suspectedIncr;
    private int confirmedIncr;
    private int curedIncr;
    private int deadIncr;
    private int seriousIncr;

    public int getSuspectedIncr() {
        return suspectedIncr;
    }

    public void setSuspectedIncr(int suspectedIncr) {
        this.suspectedIncr = suspectedIncr;
    }

    public int getConfirmedIncr() {
        return confirmedIncr;
    }

    public void setConfirmedIncr(int confirmedIncr) {
        this.confirmedIncr = confirmedIncr;
    }

    public int getCuredIncr() {
        return curedIncr;
    }

    public void setCuredIncr(int curedIncr) {
        this.curedIncr = curedIncr;
    }

    public int getDeadIncr() {
        return deadIncr;
    }

    public void setDeadIncr(int deadIncr) {
        this.deadIncr = deadIncr;
    }

    public int getSeriousIncr() {
        return seriousIncr;
    }

    public void setSeriousIncr(int seriousIncr) {
        this.seriousIncr = seriousIncr;
    }

    public int getSeriousCount() {
        return seriousCount;
    }

    public void setSeriousCount(int seriousCount) {
        this.seriousCount = seriousCount;
    }

    public Statistics() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return confirmedCount == that.confirmedCount &&
                suspectedCount == that.suspectedCount &&
                curedCount == that.curedCount &&
                deadCount == that.deadCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, modifyTime, infectSource, passWay, imgUrl, dailyPic, summary, deleted, countRemark, virus, remark1, remark2, remark3, remark4, remark5, generalRemark, confirmedCount, suspectedCount, curedCount, deadCount);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", infectSource='" + infectSource + '\'' +
                ", passWay='" + passWay + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", dailyPic='" + dailyPic + '\'' +
                ", summary='" + summary + '\'' +
                ", deleted=" + deleted +
                ", countRemark='" + countRemark + '\'' +
                ", virus='" + virus + '\'' +
                ", remark1='" + remark1 + '\'' +
                ", remark2='" + remark2 + '\'' +
                ", remark3='" + remark3 + '\'' +
                ", remark4='" + remark4 + '\'' +
                ", remark5='" + remark5 + '\'' +
                ", generalRemark='" + generalRemark + '\'' +
                ", confirmedCount=" + confirmedCount +
                ", suspectedCount=" + suspectedCount +
                ", curedCount=" + curedCount +
                ", deadCount=" + deadCount +
                ", seriousCount=" + seriousCount +
                ", suspectedIncr=" + suspectedIncr +
                ", confirmedIncr=" + confirmedIncr +
                ", curedIncr=" + curedIncr +
                ", deadIncr=" + deadIncr +
                ", seriousIncr=" + seriousIncr +
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }


    public String getInfectSource() {
        return infectSource;
    }

    public void setInfectSource(String infectSource) {
        this.infectSource = infectSource;
    }

    public String getPassWay() {
        return passWay;
    }

    public void setPassWay(String passWay) {
        this.passWay = passWay;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDailyPic() {
        return dailyPic;
    }

    public void setDailyPic(String dailyPic) {
        this.dailyPic = dailyPic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCountRemark() {
        return countRemark;
    }

    public void setCountRemark(String countRemark) {
        this.countRemark = countRemark;
    }

    public String getVirus() {
        return virus;
    }

    public void setVirus(String virus) {
        this.virus = virus;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    public String getGeneralRemark() {
        return generalRemark;
    }

    public void setGeneralRemark(String generalRemark) {
        this.generalRemark = generalRemark;
    }

    public Statistics(int id, long createTime, long modifyTime, String infectSource, String passWay, String imgUrl, String dailyPic, String summary, boolean deleted, String countRemark, String virus, String remark1, String remark2, String remark3, String remark4, String remark5, String generalRemark) {
        this.id = id;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.infectSource = infectSource;
        this.passWay = passWay;
        this.imgUrl = imgUrl;
        this.dailyPic = dailyPic;
        this.summary = summary;
        this.deleted = deleted;
        this.countRemark = countRemark;
        this.virus = virus;
        this.remark1 = remark1;
        this.remark2 = remark2;
        this.remark3 = remark3;
        this.remark4 = remark4;
        this.remark5 = remark5;
        this.generalRemark = generalRemark;
    }
}
