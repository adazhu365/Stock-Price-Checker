package edu.sitengvirginia.stockpricechecker;

import java.util.HashMap;
import java.util.Map;
/**

 Assignment Notes: Helper class provided as an object to hold
 a record coming out of the SQLite database.  Nothing should
 be edited here.

 */

public class Section {

    private String courseID;
    private String department;
    private String deptID;
    private String courseNum;
    private String courseName;
    private String semester;
    private String section;
    private String meetingType;
    private String units;
    private String status;
    private String seatsTaken;
    private String seatsOffered;
    private String instructor;
    private String meetingTime;
    private String location;
    private String lat;
    private String lon;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The courseID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     *
     * @param courseID
     * The courseID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     *
     * @return
     * The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     * The department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *
     * @return
     * The deptID
     */
    public String getDeptID() {
        return deptID;
    }

    /**
     *
     * @param deptID
     * The deptID
     */
    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    /**
     *
     * @return
     * The courseNum
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     *
     * @param courseNum
     * The courseNum
     */
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    /**
     *
     * @return
     * The courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     *
     * @param courseName
     * The courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     *
     * @return
     * The semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     *
     * @param semester
     * The semester
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     *
     * @return
     * The section
     */
    public String getSection() {
        return section;
    }

    /**
     *
     * @param section
     * The section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     *
     * @return
     * The meetingType
     */
    public String getMeetingType() {
        return meetingType;
    }

    /**
     *
     * @param meetingType
     * The meetingType
     */
    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    /**
     *
     * @return
     * The units
     */
    public String getUnits() {
        return units;
    }

    /**
     *
     * @param units
     * The units
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The seatsTaken
     */
    public String getSeatsTaken() {
        return seatsTaken;
    }

    /**
     *
     * @param seatsTaken
     * The seatsTaken
     */
    public void setSeatsTaken(String seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    /**
     *
     * @return
     * The seatsOffered
     */
    public String getSeatsOffered() {
        return seatsOffered;
    }

    /**
     *
     * @param seatsOffered
     * The seatsOffered
     */
    public void setSeatsOffered(String seatsOffered) {
        this.seatsOffered = seatsOffered;
    }

    /**
     *
     * @return
     * The instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     *
     * @param instructor
     * The instructor
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     *
     * @return
     * The meetingTime
     */
    public String getMeetingTime() {
        return meetingTime;
    }

    /**
     *
     * @param meetingTime
     * The meetingTime
     */
    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    /**
     *
     * @return
     * The location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public String getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String toString() {
        return deptID + courseNum + ": " + courseName;
    }

}