package me.mzavarzin.escience;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.swagger.v3.oas.annotations.media.Schema;

public class Request  {


/**
 *  Class variables / JSON Properties
 */
  @Schema(description = "The UUID of the experiment request")
  private String id = null;
  
  @Schema(description = "The UUID of the experiment request")
  private String name = null;
  
  @Schema(description = "The UUID of the initiator of the request")
  private String userId = null;
  
  @Schema(description = "The UUID of the Container associated")
  private String containerId = null;
  
  @Schema(description = "The UUID of the Dataset associated")
  private String datasetId = null;
  
  @Schema(description = "The UUID of the Container associated")
  private String jobId = null;
  
  @Schema(description = "The UUID of the Result associated")
  private String resultId = null;
  @Schema(description = "The status of the request")
  private String status = null;

/**
 * Getters/Setters 
 */ 
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
 
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getContainerId() {
    return containerId;
  }

  public void setContainerId(String containerId) {
    this.containerId = containerId;
  }

  public String getDatasetId() {
    return datasetId;
  }

  public void setDatasetId(String datasetId) {
    this.datasetId = datasetId;
  }
  
  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  public String getResultId() {
    return resultId;
  }

  public void setResultId(String resultId) {
    this.resultId = resultId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

/**
 * The constructors of Request
 */

public Request(String json) throws JSONException {
  
  System.out.println("2-Creating request from JSON");
  JSONObject j = new JSONObject(new JSONTokener(json.trim()));
  System.out.println(j);

  this.setId( j.getString("id"));
  this.setName(j.getString("name"));
  this.setUserId(j.getString("userId"));
  this.setContainerId(j.getString("containerId"));
  this.setDatasetId(j.getString("datasetId"));
  this.setJobId(j.getString("jobId"));
  this.setResultId(j.getString("resultId"));
  this.setStatus(j.getString("status"));

}

public JSONObject toJSON() {
		JSONObject j = new JSONObject();
		try {
			j.put("id", this.getId());
			j.put("name", this.getName());
			j.put("userId", this.getUserId());
			j.put("containerId", this.getContainerId());
			j.put("datasetId", this.getDatasetId());
      j.put("jobId", this.getJobId());
      j.put("resultId", this.getResultId());
      j.put("status", this.getStatus());
			return j;
		} catch (JSONException je) {
			je.printStackTrace();
		}
		return null;
	}

}