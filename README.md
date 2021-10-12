# UF Creator
Main role is to create the utility function formula and store it into the application’s CAMEL model. Allows the user to  specify  preferences  regarding  application  optimization  visually. The core component is called Controller; it orchestrates the process of utility function creation by invoking:

- Metric CAMEL analyser – responsible for analysing the CAMEL Model to retrieve the defined metrics

- Weights  Calculator – responsible  for  calculating  the  weights  of  each  utility  dimension.  It  may  use  a  simple weighted sum method, but it can also be extended to use various methods to retrieve and calculate weights

- Formula Creator – responsible for creating the utility function formula for the given utility metrics, templates, parameters, and weights

- Function Optimizer – responsible for optimizing the parameters of the utility function. It aims for improving the initial utility function created by the user by making it more accurate


## Application’s optimalization by user preferences:
For each utility metric the user specifies:
- The shape of the utility function
- Two metric values where the utility function value should be equal or close to 1 and where it should be equal or close to 0
- Optionally: a default utility metric formula that will be used for the initial deployment and in case of not sufficient predictions

Then, the user specifies the weights of each utility metrics. These weights should represent the importance of each utility metric and the sum of weights is equal to 1. After that, Utility Function Creator calculates and creates the overall utility function formula and stores it in the CAMEL Model.

### Method used to agregation of user preferences is Analytic Hierarchy Process: 
[Analytic Hierarchy Process](https://pl.wikipedia.org/wiki/Analytic_Hierarchy_Process)
