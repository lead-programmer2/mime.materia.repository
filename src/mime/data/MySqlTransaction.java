/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mime.data;

import java.sql.Savepoint;

/**
 * Mimics VB.Net MySql.Data.MySqlClient.MySqlTransaction class.
 * @author seph
 */
public class MySqlTransaction {
    
    private MySqlConnection _connection=null;
    private Savepoint _savepoint=null;
    
    /**
     * Creates a new instance of MySqlTransaction.
     * @param dbconnection Database connection to where this transaction was generated.
     */
    public MySqlTransaction(MySqlConnection dbconnection) {
        try {
            dbconnection.getInterface().setAutoCommit(false);
        }
        catch (Exception ex) {
            throw new MySqlTransactionException(ex.getMessage());
        }
        
        _connection=dbconnection;
    }
    
    /**
     * Creates a new instance of MySqlTransaction.
     * @param dbconnection Database connection to where this transaction was generated.
     * @param savepoint Associated database save point.
     */
    public MySqlTransaction(MySqlConnection dbconnection, Savepoint savepoint) {
        try {
            dbconnection.getInterface().setAutoCommit(false);
        }
        catch (Exception ex) {
            throw new MySqlTransactionException(ex.getMessage());
        }
   
        _connection=dbconnection; _savepoint=savepoint;
    }
    
    /**
     * Commits all recent executed SQL command statement. May throw a MySqlTransactionException whenever this method
     * encounters internal error.
     */
    public void commit() {
        java.sql.Connection _con=_connection.getInterface();
        
        try {
            _con.commit();
            _con.setAutoCommit(true);
        }
        catch (Exception ex) {
            throw new MySqlTransactionException(ex.getMessage());
        }
    }
    
    /**
     * Gets the connection to where this database transaction was instantiated.
     * @return Owner MySqlConnection object.
     */
    public MySqlConnection connection() {
        return _connection;
    }
    
    /**
     * Returns the save point associated with the current database transaction.
     * @return 
     */
    public Savepoint getSavePoint() {
        return _savepoint;
    }
    
    /**
     * Executes a database roll back based on the current database transaction save point.
     */
    public void rollBack() {
        if (_savepoint==null) throw new MySqlTransactionException("No initialized transaction save point can be restored.");
        java.sql.Connection _con=_connection.getInterface();
        
        try {
           _con.rollback();
           _con.setAutoCommit(true);
        }
        catch (Exception ex) {
            throw new MySqlTransactionException(ex.getMessage());
        }
    }
    
    /**
     * Sets the save point for the current database transaction.
     * @param savepoint 
     */
    public void setSavePoint(Savepoint savepoint) {
        _savepoint=savepoint;
    }
    
}
