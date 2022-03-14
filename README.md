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

## Research aspect:
- Testing the effectiveness of the ahp method
- Calculating  the  weights  of  each  utility  dimension

## Testing
In melodic view during uploading xmi file (FCRProactive, Genom) you should click button with Uf-Creator redirection. 
There are two possible ways of creating utility function:
### By template
1. First pick uploaded application.
2. Wait for metrics to show up.
3. Compare showed metrics with metrics which you declared in xmi or Camel model file.
4. Now you should move to the next step.
5. In the next step you should pick metrics of your choice.
6. Now you should move to the next step in which for each metric you define the shape of function and a (minimal permissible utility) and b (maximal permissible utility).
7. Now you should move to the next step in which you're setting up the weights of your dimensions (They should sum up to 1).
8. Now you should move to the next step in which the utility formula will appear. It's possible to save the utility formula into Camel model by clicking the save button. Now you're ready for deployment. 
### By function
1. First pick uploaded application.
2. Wait for metrics to show up.
3. Compare showed metrics with metrics which you declared in xmi or Camel model file.
4. Now you should move to the next step. In set of available functions check interesting ones.
5. Now the modal with formulas, variables, constants and metrics will show up. Now you should define constants and match your camel model variables, metrics with the needed ones in formula.
6. Now you should move to the next step in which you're setting up the weights of your dimensions (They should sum up to 1)
7. Now you should move to the next step in which the utility formula will appear. It's possible to save the utility formula into Camel model by clicking the save button. Now you're ready for deployment. 

## Team:

Jan Marchel (leader)

Damian Folga
