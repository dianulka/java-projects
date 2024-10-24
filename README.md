# `Java Projects`
| Description  |  Project |
| ------------- |  ------------- |
| This project implements a 2D matrix class, storing data in a 1D array, with support for matrix operations like addition, multiplication, and inversion. It includes methods for reshaping, scalar operations, and calculating the Frobenius norm. Unit tests are provided to verify the functionality.  | [matrix](matrix/Lab2) | 
| This project implements a set of classes for representing and manipulating simple mathematical expressions (functions of one or more variables) as directed acyclic graphs. It allows for building expressions, evaluating them for different variable values, printing their string representations, and performing symbolic differentiation of these expressions.  |  [mathematical_expressions](mathematical_expressions/lab5) |
| This project implements a CSVReader class to read and process administrative unit data from CSV files, such as the admin-units.csv file. The class handles CSV files with or without headers, supports different delimiters, and manages missing values. It reads rows into AdminUnit objects, storing information like population, area, and administrative levels, while handling parent-child relationships between units. The project also includes filtering, sorting, and searching functionality for the administrative units, as well as methods to calculate bounding boxes and find neighboring units based on spatial data.  |  [CSV_Reader](csv_reader_with_processing_osm/lab7) |
| This project uses Java Swing to draw a graphical Christmas tree with various decorations. The main drawing is handled in a custom DrawPanel class, using the Graphics2D object for advanced transformations like scaling and rotation. Decorations such as branches, ornaments, and lights are implemented as classes that follow the XmasShape interface, allowing for flexible transformations and rendering. The project demonstrates how to structure complex graphical components and manipulate their appearance dynamically using Java’s 2D graphics capabilities.  | [christmas tree](christmas_tree/lab10) |
| This project creates a structured document for generating a CV in HTML format using classes like Document, Section, Paragraph, and Photo. It allows adding sections with paragraphs, lists, and images, and outputs the entire document as valid HTML. The project also supports JSON serialization and deserialization using the Gson library, enabling saving and restoring the document structure.   | [cv](cv) |
| This project contains solutions to two tasks: multi-threaded prime number checking and CSV data processing. The first task involves creating PrimeChecker threads that read from a queue, check if numbers are prime, and output the results to another queue, with a performance comparison to Java's parallel streams. The second task processes a CSV file of product data, performing operations such as finding the most expensive product in a specific category, calculating the average price for certain categories, and sorting filtered results   | [kolokwium](kolokwium2024) |
| This project demonstrates multithreading concepts through a clock and file downloader tasks. The clock task creates a thread that continuously prints or visually displays the current time, using sleep() to update every second. The file downloader compares sequential and concurrent file downloads, using multiple threads and synchronization techniques like AtomicInteger and semaphores.|[threads](threads/lab11)|
| This project demonstrates parallel computation techniques by calculating the mean of large arrays using multiple threads and asynchronous programming. The primary focus is on implementing different methods for parallelizing the computation of the mean value from a large array of randomly generated numbers.|[threads](threadsV2/lab12)|
| This project implements a simple game where animated zombies move from the right to the left side of the screen, and the player's goal is to eliminate them by clicking. The game is built using Java and Swing, with animated sprites representing zombies, moving across a background image. The Zombie class handles the animation by cycling through a sprite sheet of 10 frames, updating the zombie's position and frame index in each step. An AnimationThread in the DrawPanel class manages the continuous movement and redraws the screen to create a smooth animation, simulating a 30 frames-per-second gameplay experience.|[zombie](zombie/lab14)|