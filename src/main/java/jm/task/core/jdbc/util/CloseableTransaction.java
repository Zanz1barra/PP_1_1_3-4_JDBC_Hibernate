package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.transaction.Synchronization;

public class CloseableTransaction implements AutoCloseable, Transaction {
    private final Transaction transaction;

    public CloseableTransaction(Session session) {
        transaction = session.beginTransaction();
    }

    @Override
    public void close() {
        if (this.getStatus().canRollback()) {
            this.rollback();
        }
    }

    @Override
    public TransactionStatus getStatus() {
        return transaction.getStatus();
    }

    @Override
    public void registerSynchronization(Synchronization synchronization) throws HibernateException {
        transaction.registerSynchronization(synchronization);
    }

    @Override
    public void setTimeout(int i) {
        transaction.setTimeout(i);
    }

    @Override
    public int getTimeout() {
        return transaction.getTimeout();
    }

    @Override
    public void begin() {
        transaction.begin();
    }

    @Override
    public void commit() {
        transaction.commit();
    }

    @Override
    public void rollback() {
        transaction.rollback();
    }

    @Override
    public void setRollbackOnly() {
        transaction.setRollbackOnly();
    }

    @Override
    public boolean getRollbackOnly() {
        return transaction.getRollbackOnly();
    }

    @Override
    public boolean isActive() {
        return transaction.isActive();
    }
}
