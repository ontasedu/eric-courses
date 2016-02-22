package mypackage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SKILLS", schema="MYSCHEMA") 
public class Skill {

	private int skillId = -1;
	private String description;
	private int level;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
    @Column(columnDefinition="Level INT DEFAULT 5")
    @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.INSERT)
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
