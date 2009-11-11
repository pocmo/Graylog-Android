package com.jimdo.graylog.model;
/**
 * A Log Entry as it is provided by the Graylog API
 * 
 * This class is used by the JSONDeserializer and
 * the LogMessage Class to map JSON output from
 * Graylog to Java Objects.
 * 
 * @author Sebastian Kaspari
 */
public class LogEntry {
    private String receivedAt;
    private String message;
    private String fromHost;
	private String systemID; // XXX: Type? (Null)
	private String eventCategory; // XXX: Type? (Null)
    private String deviceReportedTime;
    private int facility;
	private String eventSource; // XXX: Type? (Null)
	private String eventID; // XXX: Type? (Null)
	private String minUsage; // XXX: Type? (Null)
    private String sysLogTag;
	private String ntSeverity; // XXX: Type? (Null)
	private int id;
	private String customerId;
	private int infoUnitId;
	private String eventLogType;
	private String currUsage; // XXX: Type? (Null)
	private String maxUsage; // XXX: Type? (Null)
	private String maxAvailable; // XXX: Type? (Null)
	private String eventUser; // XXX: Type? (Null)
	private String eventBinaryData;  // XXX: Type? (Null)
	private int priority;
	private String importance;  // XXX: Type? (Null)
	private String genericFileName;  // XXX: Type? (Null)
	
	public String getReceivedAt() {
		return receivedAt;
	}
	
	public void setReceivedAt(String receivedAt) {
		this.receivedAt = receivedAt;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFromHost() {
		return fromHost;
	}
	
	public void setFromHost(String fromHost) {
		this.fromHost = fromHost;
	}
	
	public String getSystemID() {
		return systemID;
	}
	
	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}
	
	public String getEventCategory() {
		return eventCategory;
	}
	
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	public String getDeviceReportedTime() {
		return deviceReportedTime;
	}
	
	public void setDeviceReportedTime(String deviceReportedTime) {
		this.deviceReportedTime = deviceReportedTime;
	}
	
	public int getFacility() {
		return facility;
	}
	
	public void setFacility(int facility) {
		this.facility = facility;
	}
	
	public String getEventSource() {
		return eventSource;
	}
	
	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}
	
	public String getEventID() {
		return eventID;
	}
	
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	
	public String getMinUsage() {
		return minUsage;
	}
	
	public void setMinUsage(String minUsage) {
		this.minUsage = minUsage;
	}
	
	public String getSysLogTag() {
		return sysLogTag;
	}
	
	public void setSysLogTag(String sysLogTag) {
		this.sysLogTag = sysLogTag;
	}
	
	public String getNtSeverity() {
		return ntSeverity;
	}
	
	public void setNtSeverity(String ntSeverity) {
		this.ntSeverity = ntSeverity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public int getInfoUnitId() {
		return infoUnitId;
	}
	
	public void setInfoUnitId(int infoUnitId) {
		this.infoUnitId = infoUnitId;
	}
	
	public String getEventLogType() {
		return eventLogType;
	}
	
	public void setEventLogType(String eventLogType) {
		this.eventLogType = eventLogType;
	}
	
	public String getCurrUsage() {
		return currUsage;
	}
	
	public void setCurrUsage(String currUsage) {
		this.currUsage = currUsage;
	}
	
	public String getMaxUsage() {
		return maxUsage;
	}
	
	public void setMaxUsage(String maxUsage) {
		this.maxUsage = maxUsage;
	}
	
	public String getMaxAvailable() {
		return maxAvailable;
	}
	
	public void setMaxAvailable(String maxAvailable) {
		this.maxAvailable = maxAvailable;
	}
	
	public String getEventUser() {
		return eventUser;
	}
	
	public void setEventUser(String eventUser) {
		this.eventUser = eventUser;
	}
	
	public String getEventBinaryData() {
		return eventBinaryData;
	}
	
	public void setEventBinaryData(String eventBinaryData) {
		this.eventBinaryData = eventBinaryData;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getImportance() {
		return importance;
	}
	
	public void setImportance(String importance) {
		this.importance = importance;
	}
	
	public String getGenericFileName() {
		return genericFileName;
	}
	
	public void setGenericFileName(String genericFileName) {
		this.genericFileName = genericFileName;
	}
}
