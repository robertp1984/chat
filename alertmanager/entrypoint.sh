#!/usr/bin/env sh

find /etc/alertmanager -type f -name "*.tpl" \
    -exec sh -c '
        for file do
            envsubst < "$file" > "${file%.tpl}"
            echo "Converted file: $file"
        done
    ' sh {} +

/bin/alertmanager --config.file=/etc/alertmanager/alertmanager.yml --storage.path=/alertmanager

