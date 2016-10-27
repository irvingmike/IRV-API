package com.irvingmichael.irvapi.util;

import com.irvingmichael.irvapi.persistance.SessionFactoryProvider;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Aaron Anderson on 10/27/16.
 */
public final class AuthToken {

    // How long AuthToken should be valid in minutes
    private final static int TIME_TO_LIVE = 60;
    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();

    public final static String getNewToken() {

        String newToken = RandomStringUtils.random(64, true, true);

        Transaction tx = session.beginTransaction();
        SQLQuery sql = session.createSQLQuery("INSERT INTO AuthTokens (token) VALUES (:token)");
        sql.setString("token", newToken);
        sql.executeUpdate();
        tx.commit();

        return newToken;
    }

    public final static boolean valid(String token) {
        removeExpiredTokens();
        Transaction tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM AuthTokens WHERE Token=:token");
        query.setParameter("token", token);
        List list = query.list();
        return list.size() > 0;
    }

    final static void removeExpiredTokens() {
        Transaction tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM AuthTokens WHERE Created < ADDDATE(NOW(), INTERVAL -:ttl MINUTE)");
        query.setParameter("ttl", TIME_TO_LIVE);
        query.executeUpdate();
        tx.commit();
    }
}
