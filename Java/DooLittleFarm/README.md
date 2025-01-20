### About This Project
This program was originally written in Visual Studio Code.

_Last Updated: May 9, 2023_

# OVERVIEW
This program accepts a fulfillment document for a fake company named DooLittle
farms (which facilitates the distribution of baby chickens, or chicks), and
processes its `order`s as soon as possible with the chicks afforded to them by
various `shipment`s. It prioritizes satisfying the oldest orders with the oldest
shipments through a system of custom-written stacks and queues.

The fulfillment document, [src/fulfillment.txt](src/fulfillment.txt), is
organized in this format per each column (delimited by commas):

1. A shipment is denoted by an S, and an order with an O.
2. If a shipment, the number of chicks provided is provided. If an order, this
slot holds the title of the company.
3. The price per chicken sold to DooLittle Farms is provided if it is a
shipment. For an order, it is the number of chicks requested. 
4. The days passed since receiving the shipment/order.

## How to Use
Simply run `Main.java` and view the results in `output.csv` and `summary.txt`
(instructions can be found in the following section, **Output**).

## Output
Summary data is stored in a separate file from the raw output data,
[src/results/output.csv](src/results/output.csv), in
[src/results/summary.txt](src/results/summary.txt).

### `output.csv`
`output.csv` contains the chicks ordered, price per chick, and total cost
incurred by each company, as well as each company's title.

### `summary.txt`
`summary.txt` contains:

- The total number of completed sales
- The number of chicks sold
- The average price of each chick sold
- The gross sales total

## Where This Program Falls Short
In [summary.txt](src/results/summary.txt), the average chick price is
close, but not accurate. When I initially wrote the program, the result was
sufficiently proximal to the desired average for the project that it needn't be
adjusted.
