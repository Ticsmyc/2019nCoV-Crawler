package fun.ticsmyc.pojo;


import java.util.Objects;

public class TimeLine {


    /**
     * id : 192
     * pubDate : 1579861199000
     * pubDateStr : 10分钟前
     * title : 云南启动一级响应
     * summary : 根据《云南省突发公共卫生事件应急预案》，云南省新型冠状病毒感染的肺炎疫情防控工作
     * infoSource : 人民日报
     * sourceUrl : https://weibo.com/2803301701/IqZlWaeD3?from=page_1002062803301701_profile&wvr=6&mod=weibotime&type=comment#_rnd1579861293575
     * provinceId : 53
     * provinceName : 云南省
     * createTime : 1579861344000
     * modifyTime :` 1579861344000
     */

    private int id;
    private long pubDate;
    private String pubDateStr;
    private String title;
    private String summary;
    private String infoSource;
    private String sourceUrl;
    private String provinceId;
    private String provinceName;
    private long createTime;
    private long modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPubDate() {
        return pubDate;
    }

    public void setPubDate(long pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDateStr() {
        return pubDateStr;
    }

    public void setPubDateStr(String pubDateStr) {
        this.pubDateStr = pubDateStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getProvinceId() {
        return provinceId;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "id=" + id +
                ", pubDate=" + pubDate +
                ", pubDateStr='" + pubDateStr + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", infoSource='" + infoSource + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }


    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

}
