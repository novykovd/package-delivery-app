package com.example.delivery_router_project.entities;

import com.example.delivery_router_project.listeners.PackageListener;
import jakarta.persistence.*;

import java.util.List;

@Entity
@EntityListeners(PackageListener.class)
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PackageType type;
    @Enumerated(EnumType.STRING)
    private TownEnum town;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private NodeEntity startNode;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private GraphEntity graphEntity;

    public GraphEntity getGraphEntity() {
        return graphEntity;
    }

    public void setGraphEntity(GraphEntity graphEntity) {
        this.graphEntity = graphEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private NodeEntity destinationNode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private AccountEntity owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private AccountEntity courier;

    //idk ig its ok that its not explicitly connected to the node tab
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<NodeEntity>  path;

    public void setId(Long id) {
        this.id = id;
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
    }

    public void setTown(TownEnum town) {
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartNode(NodeEntity startNode) {
        this.startNode = startNode;
    }

    public void setDestinationNode(NodeEntity destinationNode) {
        this.destinationNode = destinationNode;
    }

    public AccountEntity getOwner() {
        return owner;
    }

    public void setOwner(AccountEntity owner) {
        this.owner = owner;
    }

    public AccountEntity getCourier() {
        return courier;
    }

    public void setCourier(AccountEntity courier) {
        this.courier = courier;
    }


    public Long getId() {
        return id;
    }

    public NodeEntity getStartNode() {
        return startNode;
    }

    public NodeEntity getDestinationNode() {
        return destinationNode;
    }

    public void setPath(List<NodeEntity> path) {
        this.path = path;
    }

    public List<NodeEntity> getPath() {
        return path;
    }

    public TownEnum getTown() {
        return town;
    }
}
