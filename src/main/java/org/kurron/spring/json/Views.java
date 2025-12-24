package org.kurron.spring.json;

class Views {
    interface JustText {}
    interface JustNumbers extends JustText {}
    interface JustBooleans extends JustText {}
    interface JustTime extends JustText {}
    interface JustEnumerations extends JustText {}
}