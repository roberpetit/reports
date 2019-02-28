package reports.hpqc.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class HpqcRow {
	
	@ExcelRow
	private int rowIndex;
	
	@ExcelCellName("Detected in Cycle")
	private String detectedInCycle;
	@ExcelCellName("Assigned To")
	private String user;
	@ExcelCellName("Category")
	private String category;
	@ExcelCellName("Status")
	private String status;
	@ExcelCellName("Actual Fix Time")
	private String actualFixTime;
	@ExcelCellName("Closed in Version")
	private String closedInVersion;
	@ExcelCellName("Closing Date")
	private String closingDate;
	@ExcelCellName("Defect ID")
	private String defectId;
	@ExcelCellName("Description")
	private String description;
	@ExcelCellName("Detected By")
	private String detectedBy;
	@ExcelCellName("Detected in Release")
	private String detectedInRelease;
	@ExcelCellName("Detected in Version")
	private String detectedInVersion;
	@ExcelCellName("Detected on Date")
	private String detectedOnDate;
	@ExcelCellName("Estimated Fix Time")
	private String estimatedFixTime;
	@ExcelCellName("Modified")
	private String modified;
	@ExcelCellName("Planned Closing Version")
	private String plannedClosingVerion;
	@ExcelCellName("Priority")
	private String priority;
	@ExcelCellName("Project")
	private String project;
	@ExcelCellName("Reproducible")
	private String reproducible;
	@ExcelCellName("Severity")
	private String severity;
	@ExcelCellName("Subject")
	private String subject;
	@ExcelCellName("Summary")
	private String summary;
	@ExcelCellName("Target Cycle")
	private String targetCycle;
	@ExcelCellName("Target Release")
	private String targetRelease;
	@ExcelCellName("Detected on Environment")
	private String detectedOnEnvironment;
	@ExcelCellName("DT Name")
	private String dtName;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public String getDetectedInCycle() {
		return detectedInCycle;
	}
	public void setDetectedInCycle(String detectedInCycle) {
		this.detectedInCycle = detectedInCycle;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getActualFixTime() {
		return actualFixTime;
	}
	public void setActualFixTime(String actualFixTime) {
		this.actualFixTime = actualFixTime;
	}
	public String getClosedInVersion() {
		return closedInVersion;
	}
	public void setClosedInVersion(String closedInVersion) {
		this.closedInVersion = closedInVersion;
	}
	public String getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}
	public String getDefectId() {
		return defectId;
	}
	public void setDefectId(String defectId) {
		this.defectId = defectId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetectedBy() {
		return detectedBy;
	}
	public void setDetectedBy(String detectedBy) {
		this.detectedBy = detectedBy;
	}
	public String getDetectedInRelease() {
		return detectedInRelease;
	}
	public void setDetectedInRelease(String detectedInRelease) {
		this.detectedInRelease = detectedInRelease;
	}
	public String getDetectedInVersion() {
		return detectedInVersion;
	}
	public void setDetectedInVersion(String detectedInVersion) {
		this.detectedInVersion = detectedInVersion;
	}
	public String getDetectedOnDate() {
		return detectedOnDate;
	}
	public void setDetectedOnDate(String detectedOnDate) {
		this.detectedOnDate = detectedOnDate;
	}
	public String getEstimatedFixTime() {
		return estimatedFixTime;
	}
	public void setEstimatedFixTime(String estimatedFixTime) {
		this.estimatedFixTime = estimatedFixTime;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getPlannedClosingVerion() {
		return plannedClosingVerion;
	}
	public void setPlannedClosingVerion(String plannedClosingVerion) {
		this.plannedClosingVerion = plannedClosingVerion;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getReproducible() {
		return reproducible;
	}
	public void setReproducible(String reproducible) {
		this.reproducible = reproducible;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTargetCycle() {
		return targetCycle;
	}
	public void setTargetCycle(String targetCycle) {
		this.targetCycle = targetCycle;
	}
	public String getTargetRelease() {
		return targetRelease;
	}
	public void setTargetRelease(String targetRelease) {
		this.targetRelease = targetRelease;
	}
	public String getDetectedOnEnvironment() {
		return detectedOnEnvironment;
	}
	public void setDetectedOnEnvironment(String detectedOnEnvironment) {
		this.detectedOnEnvironment = detectedOnEnvironment;
	}
	public String getDtName() {
		return dtName;
	}
	public void setDtName(String dtName) {
		this.dtName = dtName;
	}
}
