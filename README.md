# Spring Boot ActiveMQ Example

To run this example, you will need to do the following:

* Update the application.properties file with your MySQL username and password
* Create a new database schema with name springboot_activemq_example
* If you wish to used a different database / schema, you will need to override values in application.properties
* Example uses an embedded ActiveMQ Broker. If you wish to use an external broker, you will need to override the appropriate Spring Boot Properties.

Application flow:

* Once the application is started, open the same in a browser http://localhost:8080
* Create a new Product record
* Notice that the value for "Are Messages Sent" in the http://localhost:8080/product/show/1 is set to false.
* There is a link at the bottom of this record "Send message for product through queue listener"
* When this link is clicked, a message is put in an activemq queue, the browser is redirected back to the product show page without getting blocked.
* This message is read and processed by MessageListener class.
* On the browser, refreshing the page: http://localhost:8080/product/show/1
* You will see the message sent and message count increase (if you click the send link more than once)
* Expect a short delay in processing the messages.