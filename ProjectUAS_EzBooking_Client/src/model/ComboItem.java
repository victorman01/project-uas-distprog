/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rony H.I
 */
public class ComboItem{
    private String value;
    private String label;
    private String preOrderChecker;

    public ComboItem(String value, String label) {
        this.value = value;
        this.label = label;
    }
    
    public ComboItem(String value, String label, String PreOrderChecker) {
        this.value = value;
        this.label = label;
        this.preOrderChecker = PreOrderChecker;
    }

    public String getPreOrderChecker() {
        return preOrderChecker;
    }

    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return label;
    }
}
