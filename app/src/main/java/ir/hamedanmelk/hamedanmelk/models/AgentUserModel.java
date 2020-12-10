package ir.hamedanmelk.hamedanmelk.models;

import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class AgentUserModel {
    private String FirstName;
    private String LastName;
    private String AgencyName;
    private String ManagerName;
    private String AgencyPhone;
    private String Mobile;

    public AgentUserModel(String firstName, String lastName, String agencyName, String managerName, String agencyPhone, String mobile) {
        FirstName = firstName;
        LastName = lastName;
        AgencyName = agencyName;
        ManagerName = managerName;
        AgencyPhone = agencyPhone;
        Mobile = mobile;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAgencyName() {
        return AgencyName;
    }

    public void setAgencyName(String agencyName) {
        AgencyName = agencyName;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public String getAgencyPhone() {
        return AgencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        AgencyPhone = agencyPhone;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
