package models;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String id;
    private USER_TYPE userType;
    private PRIVILEGE_TYPE privilegeType = PRIVILEGE_TYPE.user;
    private STATE_TYPE stateType = STATE_TYPE.anonymous;

    public UserBean(){}
    public UserBean(String id, USER_TYPE userType, PRIVILEGE_TYPE privilegeType, STATE_TYPE stateType){
        this.id=id;
        this.userType=userType;
        this.privilegeType=privilegeType;
        this.stateType=stateType;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setStateType(STATE_TYPE stateType) {
        this.stateType = stateType;
    }

    public void setPrivilegeType(PRIVILEGE_TYPE privilegeType) {
        this.privilegeType = privilegeType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }

    public PRIVILEGE_TYPE getPrivilegeType() {
        return privilegeType;
    }

    public STATE_TYPE getStateType() {
        return stateType;
    }

    public USER_TYPE getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "userType: "+userType + " privilegeType: " + privilegeType + " stateType: "  + stateType;
    }
}




