import { useState } from "react";
import "./experimental.css";

export default function Experimental() {
  const [file, setFile] = useState(null);
  const [status, setStatus] = useState("");

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleUpload = async () => {
    if (!file) {
      alert("Please select a file");
      return;
    }

    const formData = new FormData();
    formData.append("image", file);

    try {
        setStatus("uploading...")
      const response = await fetch("http://localhost:8088/data/upload", {
        method: "POST",
        body: formData, // No need to set headers for FormData in Fetch
      });

      if (!response.ok) {
        setStatus("Failed ❌")
        throw new Error("Upload failed");
      }
      setStatus("Done ✅")
      const data = await response.json();
      console.log("Upload success:", data);
    } catch (error) {
      console.error("Upload error:", error);
    }
  };

  return (
    <div className="experimental">
      <input type="file" accept="image/*" onChange={handleFileChange} />
      <div>
        <button onClick={handleUpload}>Upload</button>
        <div style={{width: "2rem"}}></div>
        {status}
      </div>
    </div>
  );
}
