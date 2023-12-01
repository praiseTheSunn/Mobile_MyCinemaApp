CREATE TABLE mytable(
   id         INTEGER  NOT NULL PRIMARY KEY 
  ,imagePath  VARCHAR(60) NOT NULL
  ,title      VARCHAR(43) NOT NULL
  ,rating     NUMERIC(3,1) NOT NULL
  ,duration   INTEGER  NOT NULL
  ,type       VARCHAR(15) NOT NULL
  ,categories VARCHAR(17) NOT NULL
  ,synopsis   VARCHAR(235) NOT NULL
);
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (1,'movie_poster/interstellar.jpg','Interstellar',4.7,120,'IMAX','Action, Adventure','LONG LONG LONG TEXT');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (2,'movie_poster/doctor_strange_in_the_multiverse_of_madness.jpg','Doctor Strange in the multiverse of madness',4.6,130,'IMAX 3D','Action, Adventure','LONG LONG LONG TEXT');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (3,'movie_poster/eternal_sunshine_of_the_spotless_mind.jpg','Eternal Sunshine',4.5,140,'2D','Action, Adventure','LONG LONG LONG TEXT');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (4,'movie_poster/everything_everywhere_all_at_once.jpg','Everything, everywhere, all at once',5,200,'3D','Action, Adventure','LONG LONG LONG TEXT');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (5,'movie_poster/inception.jpg','Inception',4.8,160,'IMAX 2D/IMAX 3D','Action, Adventure','LONG LONG LONG TEXT');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (6,'movie_poster/la_la_land.jpg','La La Land',4.7,195,'IMAX','Action, Adventure','While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (7,'movie_poster/marvels.jpg','The Marvels',4.6,210,'IMAX 3D','Action, Adventure','Carol Danvers gets her powers entangled with those of Kamala Khan and Monica Rambeau, forcing them to work together to save the universe.');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (8,'movie_poster/memento.jpg','Memento',4.5,225,'2D','Action, Adventure','A man with short-term memory loss attempts to track down his wife''s murderer.');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (9,'movie_poster/monty_python_and_the_holy_grail.jpg','Monty Python And The Holy Grail',5,240,'3D','Action, Adventure','King Arthur and his Knights of the Round Table embark on a surreal, low-budget search for the Holy Grail, encountering many, very silly obstacles.');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (10,'movie_poster/shutter_island.jpg','Shutter Island',4.8,255,'IMAX 2D/IMAX 3D','Action, Adventure','Teddy Daniels and Chuck Aule, two US marshals, are sent to an asylum on a remote island in order to investigate the disappearance of a patient, where Teddy uncovers a shocking truth about the place.');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (11,'movie_poster/spiderman_across_the_spiderverse.jpg','Spider-Man: Across the Spider-Verse',4.7,270,'IMAX','Action, Adventure','Miles Morales catapults across the Multiverse, where he encounters a team of Spider-People charged with protecting its very existence. When the heroes clash on how to handle a new threat, Miles must redefine what it means to be a hero.');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (12,'movie_poster/taylor_swift_the_eras_tour.jpg','Taylor Swift: The Eras Tour',4.6,285,'IMAX 3D','Action, Adventure','Experience the breathtaking Eras Tour concert, performed by the one and only Taylor Swift.');
INSERT INTO mytable(id,imagePath,title,rating,duration,type,categories,synopsis) VALUES (13,'movie_poster/truman_show.jpg','The Truman Show',4.5,300,'2D','Action, Adventure','An insurance salesman discovers his whole life is actually a reality TV show.');
