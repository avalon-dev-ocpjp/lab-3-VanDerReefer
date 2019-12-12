package ru.avalon.java.ocpjp.labs;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Класс описывает представление о коде товара и отражает соответствующую 
 * таблицу базы данных Sample (таблица PRODUCT_CODE).
 * 
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ProductCode {
    /**
     * Код товара
     */
    private String code;
    /**
     * Кода скидки
     */
    private char discountCode;
    /**
     * Описание
     */
    private String description;
    /**
     * Основной конструктор типа {@link ProductCode}
     * 
     * @param code код товара
     * @param discountCode код скидки
     * @param description описание 
     */
    public ProductCode(String code, char discountCode, String description) {
        this.code = code;
        this.discountCode = discountCode;
        this.description = description;
    }
    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     * 
     * @param set {@link ResultSet}, полученный в результате запроса, 
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     */
    private ProductCode(ResultSet set) throws SQLException {
        /*
         * TODO #05 реализуйте конструктор класса ProductCode
         */
        while (set.next()) {
                code = set.getString("prod_code");
                discountCode = set.getString("discount_code").charAt(0);
                description = set.getString("description");
            }        
    }
    /**
     * Возвращает код товара
     * 
     * @return Объект типа {@link String}
     */
    public String getCode() {
        return code;
    }
    /**
     * Устанавливает код товара
     * 
     * @param code код товара
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Возвращает код скидки
     * 
     * @return Объект типа {@link String}
     */
    public char getDiscountCode() {
        return discountCode;
    }
    /**
     * Устанавливает код скидки
     * 
     * @param discountCode код скидки
     */
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }
    /**
     * Возвращает описание
     * 
     * @return Объект типа {@link String}
     */
    public String getDescription() {
        return description;
    }
    /**
     * Устанавливает описание
     * 
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Хеш-функция типа {@link ProductCode}.
     * 
     * @return Значение хеш-кода объекта типа {@link ProductCode}
     */
    @Override
    public int hashCode() {
        /*
         * TODO #06 Реализуйте метод hashCode
         */
        return code.hashCode();
                //+ description.hashCode();
    }
    /**
     * Сравнивает некоторый произвольный объект с текущим объектом типа 
     * {@link ProductCode}
     * 
     * @param obj Объект, скоторым сравнивается текущий объект.
     * @return true, если объект obj тождественен текущему объекту. В обратном 
     * случае - false.
     */
    @Override
    public boolean equals(Object obj) {
        /*
         * TODO #07 Реализуйте метод equals
         */
        if (!(obj instanceof ProductCode)) {
            return false;
        } else if (this == obj) {
            return true;
        } else {
            ProductCode productCode = (ProductCode) obj;
            return code.equals(productCode.code);

        }
    }
    /**
     * Возвращает строковое представление кода товара.
     * 
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
        /*
         * TODO #08 Реализуйте метод toString
         */
        return "Product " + description 
                + " with code " + code 
                + " has discount " + discountCode;
    }
    /**
     * Возвращает запрос на выбор всех записей из таблицы PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getSelectQuery(Connection connection) 
                                                        throws SQLException {
        /*
         * TODO #09 Реализуйте метод getSelectQuery
         */
        if (connection != null) {
            String selectQuery = "SELECT * FROM product_code";
            return connection.prepareStatement(selectQuery);
        } else {
            return null;
        }
    }
    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getInsertQuery(Connection connection) 
                                                        throws SQLException {
        /*
         * TODO #10 Реализуйте метод getInsertQuery
         */
        if (connection != null) {
            String insertQuery = "INSERT INTO product_code "
                    + "VALUES (?,?,?)";
            return connection.prepareStatement(insertQuery);
        } else {
            return null;
        }
    }
    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getUpdateQuery(Connection connection) 
                                                        throws SQLException {
        /*
         * TODO #11 Реализуйте метод getUpdateQuery
         */
        if (connection != null) {
            String updateQuery = "UPDATE APP.product_code "
                    + "SET discount_code = ?, description = ? "
                    + "WHERE prod_code = ?";
            return connection.prepareStatement(updateQuery);
        } else {
            return null;
        }
    }
    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа {@link ProductCode}
     * 
     * @param set {@link ResultSet}, полученный в результате запроса, содержащего 
     * все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException 
     */
    public static Collection<ProductCode> convert(ResultSet set) 
                                                        throws SQLException {
        /*
         * TODO #12 Реализуйте метод convert
         */
        Collection<ProductCode> productList = new ArrayList<>();
        if (set != null) {
            while (set.next()) {
                productList.add(new ProductCode(set));
            }
        }
        return productList;
    }
    /**
     * Сохраняет текущий объект в базе данных. 
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа UPDATE.
     * 
     * @param connection действительное соединение с базой данных
     */
    public void save(Connection connection) throws SQLException {
        /*
         * TODO #13 Реализуйте метод save
         */
       Collection<ProductCode> products = all(connection);
        PreparedStatement statement;
        if (products.contains(this)) {
            PreparedStatement updateStatement = getUpdateQuery(connection);
            updateStatement.setString(1, String.valueOf(discountCode));
            updateStatement.setString(2, description);
            updateStatement.setString(3, code);
            updateStatement.executeUpdate();
        } else {
            PreparedStatement insertStatement = getInsertQuery(connection);
            insertStatement.setString(1, code);
            insertStatement.setString(2, String.valueOf(discountCode));
            insertStatement.setString(3, description);
            insertStatement.execute();

        }
    }
    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов
     * типа {@link ProductCode}
     * 
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link ProductCode}
     * @throws SQLException 
     */
    public static Collection<ProductCode> all(Connection connection) 
                                                        throws SQLException {
        try (PreparedStatement statement = getSelectQuery(connection)) {
            try (ResultSet result = statement.executeQuery()) {
                return convert(result);
            }
        }
    }
}
