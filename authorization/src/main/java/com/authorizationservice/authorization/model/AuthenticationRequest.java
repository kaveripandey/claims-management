package com.authorizationservice.authorization.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor      
@AllArgsConstructor
@ToString
@Entity
@ApiModel(description = "Creating model class for user logging in")

public class AuthenticationRequest {

    @Id
    @ApiModelProperty(value = "username of the Customer doing login")
    private String username;

    @ApiModelProperty(value = "Password of the Customer doing login")
    private String password;

}
