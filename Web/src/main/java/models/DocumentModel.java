package models;

import beans.Category;
import beans.Document;
import org.sql2o.Connection;
import utils.DbUtils;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public class DocumentModel {


    public static List<Document> getAll() {
        final String sql = "select * from documents";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(sql).executeAndFetch(Document.class);
        }
    }

    public static void add(Document d) {
        final String sql = "INSERT INTO documents (CatID,CatName,CatDemo) VALUES (:catid,:catname,:catdemo)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("catid", d.getCatID())
                    .addParameter("catname", d.getCatName())
                    .addParameter("catdemo", d.getCatDemo())
                    .executeUpdate();
        }
    }
    public static Optional<Document> findById(int id) {
        final String sql = "select * from documents where CatID = :CatID";
        try (Connection con = DbUtils.getConnection()) {
            List<Document> list = con.createQuery(sql)
                    .addParameter("CatID", id)
                    .executeAndFetch(Document.class);

            if (list.size() == 0) {
                return Optional.empty();
            }

            return Optional.ofNullable(list.get(0));
        }
    }



    public static void delete(int id) {
        final String sql = "delete from documents where CatID = :CatID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("CatID", id)
                    .executeUpdate();
        }
    }

    public static void update(Category c) {
        final String sql = "update documents set CatName = :CatName where CatID = :CatID";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("CatID", c.getCatID())
                    .addParameter("CatName", c.getCatName())
                    .executeUpdate();
        }
    }

}
