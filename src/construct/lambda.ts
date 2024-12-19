import {
  aws_lambda as lambda,
} from "aws-cdk-lib";
import { Construct } from "constructs";
import { join } from "path";
import * as cdk from "aws-cdk-lib";

// api-gatewayのリクエストを分割してDynamoDBに保存するLambda関数
export class ReqBroker extends Construct {
  constructor(scope: Construct, id: string) {
    super(scope, id);

    // root/.gradleを利用しないように, GRADLE_USER_HOMEを設定 
    // permission deniedエラー回避
    // Exception in thread "main" java.lang.RuntimeException: Could not create parent directory for lock file /.gradle/wrapper/dists/gradle-8.4-bin/1w5dpkrfk8irigvoxmyhowfim/gradle-8.4-bin.zip.lck
    const bundlingAssets = lambda.Code.fromAsset(
      join(__dirname, "../../software", "HelloWorldFunction"), {
      bundling: {
        image: lambda.Runtime.JAVA_21.bundlingImage,
        bundlingFileAccess: cdk.BundlingFileAccess.VOLUME_COPY,
        command: [
          "bash", "-c",
          [
            "rm -rf build/libs",
            "export GRADLE_USER_HOME=/asset-input/.gradle",
            "./gradlew shadowJar",
            "cp build/libs/*.jar /asset-output/",
          ].join(" && "),
        ],
      }
    })

    // JVMの起動に多少のメモリが必要
    // 128MB, 256MBだと起動に時間がかかるので, timeoutを10sに設定
    new lambda.Function(this, "ReqBroker", {
      runtime: lambda.Runtime.JAVA_21,
      handler: "helloworld.App::handleRequest",
      memorySize: 256,
      timeout: cdk.Duration.seconds(10),
      code: bundlingAssets,
    })
  }
}