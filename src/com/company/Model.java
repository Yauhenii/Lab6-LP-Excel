package com.company;

import javax.swing.table.DefaultTableModel;

public class Model extends DefaultTableModel {

    public static final Object[] tableHeader = new String[]{"","A","B","C","D","E","F","G","H","I","J","K","L,","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public Model() {
        super(tableHeader, 0);
    }

}
