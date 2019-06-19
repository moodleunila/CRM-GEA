package unila.edu.mx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vtiger_cf_858"
    ,catalog="vtiger"
)
public class VtigerCf858  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer cf858id;
    
	private String cf858;
    
	private Integer sortorderid;
    
	private int presence;
    
	private String color;

    public VtigerCf858() {
    }

	
    public VtigerCf858(String cf858, int presence) {
        this.cf858 = cf858;
        this.presence = presence;
    }
    public VtigerCf858(String cf858, Integer sortorderid, int presence, String color) {
       this.cf858 = cf858;
       this.sortorderid = sortorderid;
       this.presence = presence;
       this.color = color;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="cf_858id", unique=true, nullable=false)
    public Integer getCf858id() {
        return this.cf858id;
    }
    
    public void setCf858id(Integer cf858id) {
        this.cf858id = cf858id;
    }

    
    @Column(name="cf_858", nullable=false, length=200)
    public String getCf858() {
        return this.cf858;
    }
    
    public void setCf858(String cf858) {
        this.cf858 = cf858;
    }

    
    @Column(name="sortorderid")
    public Integer getSortorderid() {
        return this.sortorderid;
    }
    
    public void setSortorderid(Integer sortorderid) {
        this.sortorderid = sortorderid;
    }

    
    @Column(name="presence", nullable=false)
    public int getPresence() {
        return this.presence;
    }
    
    public void setPresence(int presence) {
        this.presence = presence;
    }

    
    @Column(name="color", length=10)
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}


