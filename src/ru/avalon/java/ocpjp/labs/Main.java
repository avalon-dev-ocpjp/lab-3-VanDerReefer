package ru.avalon.java.ocpjp.labs;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */
        try (Connection connection = getConnection()) {
            ProductCode code = new ProductCode("MO", 'N', "Movies");
            Collection<ProductCode> arrayList = (ArrayList<ProductCode>) 
                                                ProductCode.all(connection);

            arrayList = ProductCode.all(connection);
            code.save(connection);
            printAllCodes(connection);

            code.setCode("MV");
            code.save(connection);
            printAllCodes(connection);
        }
        /*
         * TODO #14 Средствами отладчика проверьте корректность работы программы
         */
    }
    /**
     * Выводит в кодсоль все коды товаров
     * 
     * @param connection действительное соединение с базой данных
     * @throws SQLException 
     */    
    private static void printAllCodes(Connection connection) throws SQLException {
        Collection<ProductCode> codes = ProductCode.all(connection);
        for (ProductCode code : codes) {
            System.out.println(code);
        }
    }
    /**
     * Возвращает URL, описывающий месторасположение базы данных
     * 
     * @return URL в виде объекта класса {@link String}
     */
    private static String getUrl() throws IOException {
        /*
         * TODO #02 Реализуйте метод getUrl
         */
        return getProperties().getProperty("url");
    }
    /**
     * Возвращает параметры соединения
     * 
     * @return Объект класса {@link Properties}, содержащий параметры user и 
     * password
     */
    private static Properties getProperties() throws IOException {
        /*
         * TODO #03 Реализуйте метод getProperties
         */
        String propPath = System.getProperty("user.dir") + 
                "\\src\\ru\\avalon\\java\\j30\\labs\\resources\\database.properties";
        // пришлось так интерпритировать пути из-за корпоративной операционки
        // французкая Windows + MUI
        
        Properties info = new Properties();
        try (InputStream is = new FileInputStream(
                propPath)) {
            info.load(is);
        }
        return info;
    }
    /**
     * Возвращает соединение с базой данных Sample
     * 
     * @return объект типа {@link Connection}
     * @throws SQLException 
     */
    private static Connection getConnection() throws SQLException, IOException {
        /*
         * TODO #04 Реализуйте метод getConnection
         */
        return DriverManager.getConnection(getUrl(), getProperties());
    }
    
}
