How to work with XML and JSON

Serialization and Marshalling : check SJavaPhilosophy.docx
Task: Jackson also can serialize XML. Check, read, learn and summarized:
https://www.baeldung.com/jackson-linkedhashmap-cannot-be-cast
https://www.baeldung.com/jackson-xml-serialization-and-deserialization


JSON - JavaScript Object Notation
Libraries: 
 - GSON
    (Demonstration start point class : net.kiranatos.json.gson.Demo01BaseGSON)
 - Jackson https://github.com/FasterXML/jackson-databind
    (Demonstration start point class : net.kiranatos.json.jackson.Demo01BaseJackson)

Валидатор: https://jsonformatter.org
Covertors: 
    https://json2csharp.com/json-to-pojo (JSON,XML -> Java,C#)
    https://codebeautify.org/

{} - Запись. Неупорядоченное множество пар "ключ":"значение".
[] - Массив. Упорядоченное множество значений.
{
 "key" : "value",
 "param1" : 33,
 "param2" : true,
 "numArray" : [100,2,3], 
 
"objArray" : [{
    "param5" : 55,
    "param6" : false
 }],

 "NestedObject" : {
    "NestedParam" : 55,
    "nestedStrArray" : ["text","Some Text","TEXT"]
 }
}

Types:
Strings     "Hello World" "Kyle" "I"
Numbers     10 1.5 -30 1.2e10
Booleans    true false
null        null
Arrays      [1,2,3] ["Hello","World"]
Objects     {"key":"value"} {"age":30}

XML :
Libraries: DOM, JAXB, SAX, XPath 
    DOMCreator - create simple file(coursesDOMCreater.xml)
    DOMParser - read info from file(coursesDOMParser.xml)
    DOMUpdater - read from coursesDOMParser.xml, update info and write in updatedCoursesDOMUpdater.xml
    JAXBConverter - have two methods for marshalling and demarshalling in file coursesJAXBConverter.xml
 SAX, XPath - поверхностно прошёл

JSON : 
    jackson: в jackson/readme изучить как работать с аннотациями
    Demo01Basic - базовые вещи, как (де)сериализовать класс
    Demo02Collection - как (де)сериализовать коллекции