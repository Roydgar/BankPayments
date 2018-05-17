package ua.training.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.impl.JDBCDaoFactory;


public abstract class DaoFactory{
    private static DaoFactory daoFactory;

    public Logger logger = LogManager.getRootLogger();


    public abstract UserDao createUserDao();
    public abstract AccountDao createAccountDao();
    public abstract UserHasAccountDao createUserHasAccountDao();
    public abstract OperationDao createOperationDao();
    public abstract CreditRequestDao createCreditRequestDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory =  new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
