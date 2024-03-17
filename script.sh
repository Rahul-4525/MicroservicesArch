#!/bin/bash
docker_files=("./AccountService/dockerfile" "./discovery/dockerfile" "./AuthenticationService/dockerfile" "./gateway/dockerfile" "./NotificationService/dockerfile" "./PaymentInitService/dockerfile" "./PaymentProcessingService/dockerfile")
image_names=("account_service" "discovery_service" "authentication_service" "gateway_service" "notification_service" "payment_init_service" "payment_process_service")
INDEX=0
for i in $docker_files; do
    docker build --no-cache -t "${image_names[$INDEX]}":latest . -f "${docker_files[$INDEX]}"
    INDEX=${INDEX}+1
done