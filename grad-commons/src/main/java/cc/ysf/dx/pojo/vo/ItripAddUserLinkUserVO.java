package cc.ysf.dx.pojo.vo;


import java.io.Serializable;

/**
 * 前端输入-新增常用联系人VO
 * Created by donghai on 2017/5/10.
 */
public class ItripAddUserLinkUserVO implements Serializable {

    private static final long serialVersionUID = 6973885863495947990L;
    private String linkUserName;
    private Integer linkIdCardType;
    private String linkIdCard;
    private String linkPhone;
    private Long userId;

    public void setLinkUserName (String  linkUserName){
        this.linkUserName=linkUserName;
    }

    public  String getLinkUserName(){
        return this.linkUserName;
    }
    public void setLinkIdCard (String  linkIdCard){
        this.linkIdCard=linkIdCard;
    }

    public Integer getLinkIdCardType() {
        return linkIdCardType;
    }

    public void setLinkIdCardType(Integer linkIdCardType) {
        this.linkIdCardType = linkIdCardType;
    }

    public  String getLinkIdCard(){
        return this.linkIdCard;
    }
    public void setLinkPhone (String  linkPhone){
        this.linkPhone=linkPhone;
    }

    public  String getLinkPhone(){
        return this.linkPhone;
    }
    public void setUserId (Long  userId){
        this.userId=userId;
    }

    public  Long getUserId(){
        return this.userId;
    }
}
