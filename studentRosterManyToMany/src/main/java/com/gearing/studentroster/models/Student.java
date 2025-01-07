package com.gearing.studentroster.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dorm_id")
	private Dorm dorm;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name="classes_students",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "class_id")
	)
	private List<AvailableClass> classes;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date updatedAt;
	
	public Student() {
		
	}
	
	public Student(String name) {
		this.name = name;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dorm getDorm() {
		return dorm;
	}

	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<AvailableClass> getClasses() {
		return classes;
	}

	public void setClasses(List<AvailableClass> classes) {
		this.classes = classes;
	}
	
}
