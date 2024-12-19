import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import { ReqBroker } from '../src/construct/lambda';
// import * as sqs from 'aws-cdk-lib/aws-sqs';

export class LambdaJavaDynamodbStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    // The code that defines your stack goes here

    new ReqBroker(this, 'Handler');
    // example resource
    // const queue = new sqs.Queue(this, 'LambdaJavaDynamodbQueue', {
    //   visibilityTimeout: cdk.Duration.seconds(300)
    // });
  }
}
