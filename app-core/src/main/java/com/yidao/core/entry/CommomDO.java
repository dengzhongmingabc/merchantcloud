package com.yidao.core.entry;


import javax.persistence.*;
import java.util.Date;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "commom_do")
public class CommomDO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column()
	private String userName;
	@Column()
	private String password;
	@Column()
	private String realName;
	@Column()
	private String empNumber;
	@Column()
	private String department;
	@Column()
	private String position;
	@Column()
	private Date createdDate;
	@Column()
	private Long parentId;
	@Column()
	private Long departmentId;
	@Column()
	private Boolean proxyAdmin;
	@Column()
	private Double balance=0.0;
	@Column()
	private String mobile;
	@Column()
	private Integer points = 0;
	@Column()
	private Integer wrongCount=0;
	@Column()
	private String phonePath;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Boolean getProxyAdmin() {
		return proxyAdmin;
	}

	public void setProxyAdmin(Boolean proxyAdmin) {
		this.proxyAdmin = proxyAdmin;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getWrongCount() {
		return wrongCount;
	}

	public void setWrongCount(Integer wrongCount) {
		this.wrongCount = wrongCount;
	}

	public String getPhonePath() {
		return phonePath;
	}

	public void setPhonePath(String phonePath) {
		this.phonePath = phonePath;
	}
}