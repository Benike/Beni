# Online amusement park!

## [Available at: amusement-park.eu-central-1.elasticbeanstalk.com](http://amusement-park.eu-central-1.elasticbeanstalk.com)

If you want to visit my online amusement park, you have to register. After you register you will have 500 spending money. You can upload more money with the 'Upload money' button in the navbar, at the moment, uploading money is free, but in the future it will be replaced with bank card payment.
On the main page, there are amusement parks in a filterable and pageable table, the search fields are hidden, click on the 'Search' button in the navbar if you want to search, click on a row to see more information about the amusement park. You can see the amusement park's guest book registries and you can enter by clicking on the 'Enter to the park' button, but be careful, the amusement park's entrance fee will be subtracted from your spending money if you enter.
After entering, you will see the machines inside the amusement park in a table. There is a 'Get on' button in every row, if you click on it, you have to pay the machine's ticket price and you will see a 3D video. There are 5 different machine types with 5 different videos. The dialog will be closed after a time or you can click on the 'Get off' button to get off from the machine. Inside an amusement park, you can write to the guest book by clicking the 'Guest Book Writing' button in the navbar and filling the form.

If a visitor is in an amusement park or on a machine, then they are connected in the database.

### Requirements:

- JDK-11
- Maven 3
- Docker in Swarm mode runnable without sudo

#### Users:

- Admin: email:'nembence1994@gmail.com', password:'Pass1234'
- Visitor: email:'fenicser85@gmail.com', password:'Pass1234'

#### Postgresql dev run

- Run 'dockerRunPostgresWithAdmin.sh' to start postgres with tables, initial data and pgadmin.
- Access pgadmin at localhost:8079 email:'nembence1994@gmail.com' pass:'admin'.
- Host:'db', database:'amusement_park', user:'postgres', pass:'admin'.
