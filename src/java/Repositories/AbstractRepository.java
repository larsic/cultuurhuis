/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import javax.sql.DataSource;

abstract class AbstractRepository {

    public final static String JNDI_NAME = "jdbc/cultuurhuis";
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
