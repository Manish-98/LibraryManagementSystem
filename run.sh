set -eo pipefail
mkdir -p "$MNT_DIR"

echo "Mounting GCS Fuse."
gcsfuse --debug_gcs --debug_fuse library-management-system "$MNT_DIR"
echo "Mounting completed."

java -jar LibraryManagementSystem.jar

wait -n
