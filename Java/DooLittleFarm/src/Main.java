import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs the program. It accepts input information in the form of a
 * file containing shipment and order information for a fake company named
 * Doolittle Farms, and processes said data. It then writes raw output data to
 * output.csv and summary data to summary_data.txt in /src/results.
 * 
 * @author Sean Loesch
 * Date:   5/9/2023
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // Creating a stack of shipments and a queue of customer orders 
        ALStack shipments = new ALStack();
        ALQueue orders = new ALQueue();

        File inputFile = new File("src/fulfillment.txt");
        Scanner reader = new Scanner(inputFile);

        // Running the program
        runProgram(shipments, orders, inputFile, reader);

        // Writes summary data from output.csv
        writeSummaryData();
    }

    /**
     * Runs the bulk of the program, and stores the raw output data in
     * results/output.csv.
     * 
     * @param shipments the shipments to the stock, stored in a stack
     * @param orders the orders requesting chicks, stored in a stack
     * @param inputFile the file containing the shipments and orders in
     * chronological order
     * @param reader a scanner for reading inputFile
     * @throws IOException
     */
    public static void runProgram(ALStack shipments, ALQueue orders,
                                  File inputFile, Scanner reader)
                                  throws IOException {
        // Creating output csv
        FileWriter outputFile = new FileWriter("src/results/output.csv");
        outputFile.write("company,chicksOrdered,pricePerChick,totalCost");

        // While the file hasn't been completely processed
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] dataArr = data.split(",");

            // Storing current line as either stack or queue
            // If current item is an order
            if (dataArr[0].equals("O")) {
                // Increment the age of all existing shipments
                int daysPassed = Integer.parseInt(dataArr[3]
                                                  .replaceAll(" ", ""));
                shipments.incrementContentsAge(daysPassed);
                orders.add(new Order(dataArr[1].trim(), 
                                     Integer.parseInt(dataArr[2]
                                                      .replaceAll(" ", "")),
                                     0));
            }
            // If current item is a shipment
            else if (dataArr[0].equals("S")) {
                // Increment the age of all existing shipments
                int daysPassed = Integer.parseInt(dataArr[3]
                                                  .replaceAll(" ", ""));
                shipments.incrementContentsAge(daysPassed);

                shipments.push(new Shipment(Integer.parseInt(dataArr[1]
                                            .replaceAll(" ", "")),
                                            Double.parseDouble(dataArr[2]
                                            .replaceAll(" ", "")),
                                            0));
            }
            // If the line doesn't contain "O" or "S" at the beginning
            else {
                System.out.println("Error occured: neither an order or a " + 
                                   "shipment was specified. Check the " +
                                   "integrity of the fulfillment document " +
                                   "and try again.");
            }

            // Processing as many orders from the queue using current stock in
            // the stack
            if (orders.isEmpty()) {
                continue;
            }
            // There are pending orders
            else {
                // There are no chicks in stock
                if (shipments.isEmpty()) {
                    continue;
                }
                // There are chicks in stock
                else {
                    Order currOrder = orders.peek();

                    // If there are enough chicks in stock to satisfy the most
                    // recent order
                    if (shipments.getNumChicks() >= currOrder
                        .getChicksRequested()) {
                        Shipment currShipment = shipments.peek();

                        // Stores total price for a singular order
                        double orderPrice = 0;

                        // Storing original number of chicks in the order so it
                        // can be written to the output file accurately later
                        int orderChicks = currOrder.getChicksRequested();

                        // While there are enough chicks in the shipments stack
                        // to satisfy the oldest order and there are orders,
                        // fulfill the order
                        while (shipments.getNumChicks() >= currOrder
                               .getChicksRequested() && !orders.isEmpty()) {
                            // If the most recent shipment has enough to cover
                            // the order
                            if (currShipment.getNumChicks() >= currOrder
                                .getChicksRequested()) {
                                // Add shipment's price to total for order
                                orderPrice += currOrder.getChicksRequested() *
                                (currShipment.getPrice() + .1 *
                                 currShipment.getDays());

                                // Reduce number of chicks in this shipmnent by
                                // the number that was bought with this order
                                currShipment.setNumChicks(currShipment
                                                          .getNumChicks() - 
                                                          currOrder
                                                          .getChicksRequested());
                                // Updating number of chicks in the stack
                                shipments.updateNumChicks();

                                // Writing to file after payment has been
                                // processed
                                outputFile.write(String.format("\n" + currOrder
                                                               .getName() + 
                                                               "," +
                                                               orderChicks + ","
                                                               +
                                                               "%.2f," + "%.2f",
                                                               (orderPrice / 
                                                                orderChicks), 
                                                               orderPrice));

                                // Removing finished order and resetting
                                // orderPrice for next order
                                orders.poll();
                                orderPrice = 0;

                                // If there are is another order after the one
                                // that was just paid out
                                if (!orders.isEmpty()) {
                                    currOrder = orders.peek();

                                    // Update orderChicks to represent current
                                    // order
                                    orderChicks = currOrder.getChicksRequested();
                                }

                                // If there were exactly enough chicks in the
                                // shipment to fulfill the order
                                if (currShipment.getNumChicks() == 0) {
                                    shipments.pop();
                                    // If there is another shipment left after
                                    // this one was exhausted
                                    if (!shipments.isEmpty()) {
                                        currShipment = shipments.peek();
                                    }
                                }
                            }
                            // If the most recent shipment doesn't have enough
                            // to cover the order
                            else {
                                // Add shipment's price to total for order
                                // before getting rid of it 
                                orderPrice += currShipment.getNumChicks() *
                                (currShipment.getPrice() + .1 *
                                 currShipment.getDays());
                                
                                currOrder.setChicksRequested(currOrder
                                                             .getChicksRequested()
                                                             - currShipment
                                                             .getNumChicks());

                                shipments.pop();
                                currShipment = shipments.peek();

                                shipments.updateNumChicks();
                            }
                        }
                    }
                }
            }
        }
        // Finishing up
        reader.close();
        outputFile.close();
    }

    /**
     * Writes summary data for the output in results/summary_data.txt.
     * 
     * @throws IOException
     */
    private static void writeSummaryData() throws IOException {
        Scanner reader = new Scanner(new File("src/results/output.csv"));
        // Skipping first line of the csv
        reader.nextLine();

        // Initializing relevant summary data\
        int saleCount = 0;
        int chicksSold = 0;
        ArrayList<Double> chickPrices = new ArrayList<Double>();
        double grossSalesTotal = 0;

        // Extracting summary data from raw output data
        while (reader.hasNextLine()) {
            saleCount++;

            String data = reader.nextLine();
            String[] dataArr = data.split(",");
            chicksSold += Integer.parseInt(dataArr[1]);

            chickPrices.add(Double.parseDouble(dataArr[2]));

            grossSalesTotal += Double.parseDouble(dataArr[3]);
        }

        // Calculating average chick price
        double sum = 0;
        for (int i = 0; i < chickPrices.size(); i++) {
            sum += chickPrices.get(i);
        }
        double averageChickPrice = sum / chickPrices.size();

        // Writing summary data
        FileWriter outputFile = new FileWriter("src/results/summary.txt");
        outputFile.write("Summary Statistics:\n\n");
        outputFile.write("Total number of completed sales: " + saleCount + "\n");
        outputFile.write("Total number of chicks sold: " + chicksSold + "\n");
        outputFile.write(String.format("Average price of each chick sold: " +
                                       "%.2f\n", averageChickPrice));
        outputFile.write(String.format("Gross Sales Total: %.2f",
                                        grossSalesTotal));

        reader.close();
        outputFile.close();
    } 
}
