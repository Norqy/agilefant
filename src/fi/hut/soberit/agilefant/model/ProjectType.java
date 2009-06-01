package fi.hut.soberit.agilefant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Type;

import flexjson.JSON;

/**
 * Hibernate entity bean representing an activity type.
 * <p>
 * Conceptually, ProjectType represents a work entitity by defining some
 * WorkTypes which are applicable to this kind of an activity. Activity type of
 * "customer software project" might contain work types "planning", "coding" and
 * "customer support".
 * <p>
 * ProjectType has a target percentage, which is defined as the percentage of
 * all the work that should be spent to work which are under this particular
 * ProjectType, given by company leaders or such.
 * 
 * @see fi.hut.soberit.agilefant.model.WorkType
 */
@Entity
@BatchSize(size = 20)
@Table(name = "projecttypes")
public class ProjectType implements Comparable<ProjectType> {

    private int id;

    private String name;

    private String description;

    /**
     * Get the id of this object.
     * <p>
     * The id is unique among all activity types.
     */
    // tag this field as the id
    @Id
    // generate automatically
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JSON
    public int getId() {
        return id;
    }

    /**
     * Set the id of this object.
     * <p>
     * You shouldn't normally call this.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Type(type = "escaped_text")
    @JSON
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false, unique = true)
    @Type(type = "escaped_truncated_varchar")
    @JSON
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(ProjectType o) {
        if (o == null) {
            return -1;
        }
        return getName().compareTo(o.getName());
    }

}